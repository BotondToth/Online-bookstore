package hu.szte.bookstore.service;

import com.itextpdf.text.DocumentException;
import hu.szte.bookstore.controller.SaleController;
import hu.szte.bookstore.exception.UserNotFoundException;
import hu.szte.bookstore.model.User;
import hu.szte.bookstore.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Session;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;
import java.util.stream.Stream;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * Email kuldo service
 * @author Robi
 */
@Service
@Slf4j
public class EmailSenderServiceImpl {

    private String username = "konyvesbolt2018@gmail.com";

    private String passw = "konyvesbolt";

    private SaleController saleController;

    /**
     * Rendelesrol kuld emailt
     * @param user a bejelentkezett felhasznalo
     * @param saleController a vasarlashoz hasznalt controller
     */
    public void sendEmailAboutOrder(final User user, final SaleController saleController, final int used) throws DocumentException, IOException, UserNotFoundException {
        if (user == null) {
            throw new UserNotFoundException(user);
        }
        this.saleController = saleController;
        log.info("Sending email to: " + user.getEmail() + " , about order");
        Properties props = createProperties();

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, passw);
                    }
                });
        try {
            createDocument(user, used);
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(user.getEmail()));
            message.setSubject("Vásárlás visszaigazolás");
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Köszönjük a rendelését! A számláját csatoltuk ehhez az emailhez.\n\n"
                    + " Könyvesbolt, 2018");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            String filename = "szamla_" + getTodaysDate() + ".pdf";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);
            log.info("Email sent!");
            deletePdfFile();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private Properties createProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        return props;
    }

    private void createDocument(final User user, final int used) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("szamla_" + getTodaysDate() + ".pdf"));
        document.open();
        Paragraph titleParagraph = new Paragraph("Számla", new Font(Font.FontFamily.HELVETICA, 22));
        titleParagraph.setAlignment(Element.ALIGN_CENTER);
        titleParagraph.setSpacingAfter(50);
        Paragraph dateParagraph = new Paragraph("Számla kiállítási ideje: " + getTodaysDate(), new Font(Font.FontFamily.HELVETICA, 12));
        Paragraph ending = new Paragraph("Rendszerfejlesztés 1", new Font(Font.FontFamily.HELVETICA, 12));
        ending.setAlignment(Element.ALIGN_CENTER);
        ending.setPaddingTop(50);
        PdfPTable table = new PdfPTable(3);
        PdfPTable priceTable = new PdfPTable(2);
        Paragraph userDetails = new Paragraph("Vevő neve: " + user.getLastName() + " " + user.getFirstName(), new Font(Font.FontFamily.HELVETICA, 12));
        Paragraph sellerDetails = new Paragraph("Számlát kibocsátó adatai: Könyvesbolt Kft.");
        sellerDetails.setSpacingAfter(30);
        userDetails.setAlignment(Element.ALIGN_LEFT);
        userDetails.setPaddingTop(50);
        priceTable.setPaddingTop(50);
        addTableHeader(table);
        addContentRows(table);
        addPriceParagraph(priceTable, used);
        document.add(titleParagraph);
        document.add(table);
        document.add(userDetails);
        document.add(sellerDetails);
        document.add(priceTable);
        document.add(dateParagraph);
        document.add(ending);
        document.close();
    }

    private void deletePdfFile() {
        try {
            File file = new File("szamla_" + getTodaysDate() + ".pdf");
            if (file.delete()) {
                log.info("Invoice deleted!");
            } else {
                log.info("Delete operation failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addTableHeader(final PdfPTable table) {
        Stream.of("Cím", "Szerz\u0151", "Ár").forEach(columnTitle -> {
            PdfPCell header = new PdfPCell();
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setBorderWidth(1);
            header.setPhrase(new Phrase(columnTitle));
            table.addCell(header);
        });

        table.completeRow();
    }

    private void addContentRows(final PdfPTable table) {
        for (Book book : saleController.getBasket()) {
            table.addCell(book.getTitle());
            table.addCell(book.getAuthor());
            table.addCell(String.valueOf(book.getPrice()) + " Ft");
            table.completeRow();
        }

        table.completeRow();
    }

    private void addPriceParagraph(final PdfPTable priceTable, final int used) {

        final int fullPrice = saleController.getBasketTotalSum();
        double noVAT = fullPrice * 0.73;
        final String noVatFormatted = String.format("%.2f", noVAT);
        priceTable.addCell("Nettó ár:");
        priceTable.addCell(noVatFormatted + " Ft");

        if (used != 0) {
            priceTable.addCell("Beváltott könyvek miatti kedvezmény:");
            priceTable.addCell(used * 1000 + " Ft");
        }

        priceTable.addCell("Teljes összeg:");
        final int moneyToPayAfterExchange = used * 1000 >= fullPrice ? 0 : fullPrice - used * 1000;
        priceTable.addCell(moneyToPayAfterExchange + " Ft");

        priceTable.completeRow();
    }

    private LocalDate getTodaysDate() {
        return LocalDate.now();
    }

    public void sendEmailAboutRegistration(final User user) throws MessagingException {
        Properties props = createProperties();

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, passw);
                    }
                });
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(user.getEmail()));
        message.setSubject("Köszönjük a regisztrációt!");
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText("Kedves " + user.getLastName() + "!\n\nKöszönjük, hogy regisztráltál könyvesboltunkba! Kellemes böngészést kívánunk!.\n\n"
                + " Könyvesbolt, 2018");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        message.setContent(multipart);
        Transport.send(message);
        log.info("Email sent about registration!");
    }
}