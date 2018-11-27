package hu.szte.bookstore.service;

import com.itextpdf.text.DocumentException;
import hu.szte.bookstore.controller.SaleController;
import hu.szte.bookstore.model.User;
import hu.szte.bookstore.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.stream.Stream;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javax.mail.*;
import javax.mail.internet.*;

@Service
public class EmailSenderServiceImpl {

    private static final Logger log = Logger.getLogger(EmailSenderServiceImpl.class.getName());

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String passw = "konyvesbolt";

    @Autowired
    private SaleController saleController;

    public void sendEmailAboutOrder(final User user) throws DocumentException, IOException {
        log.info("Sending email to: " + " , about order");
        Properties props = createProperties();

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, passw);
                    }
                });
        try {
            createDocument(user);
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
        props.put("mail.smtp.port", "465");
        return props;
    }

    private void createDocument(final User user) throws FileNotFoundException, DocumentException {
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
        PdfPTable table = new PdfPTable(4);
        PdfPTable priceTable = new PdfPTable(2);
        Paragraph userDetails = new Paragraph("Vevo neve: " + user.getLastName() + " " + user.getFirstName(), new Font(Font.FontFamily.HELVETICA, 12));
        Paragraph sellerDetails = new Paragraph("Számlát kibocsátó adatai: Könyvesbolt Kft., 1061 Budapest, Andrássy út 4.");
        sellerDetails.setSpacingAfter(30);
        userDetails.setAlignment(Element.ALIGN_LEFT);
        userDetails.setPaddingTop(50);
        priceTable.setPaddingTop(50);
        addTableHeader(table);
        addContentRows(table, user);
        addPriceParagraph(user, priceTable);
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

    private void addTableHeader(PdfPTable table) {
        Stream.of("Cím", "Szerzo", "Megvásárolt db", "Ár")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addContentRows(PdfPTable table, final User user) {
        for (Book book : saleController.getBasket()) {
            System.err.println(book.toString());
            table.addCell(book.getTitle());
            table.addCell(book.getAuthor());
            table.addCell(book.getPublisher());
        }

        table.completeRow();
    }

    private void addPriceParagraph(final User user, PdfPTable priceTable) {

        final int fullPrice = saleController.getBasketTotalSum();
        double noVAT = fullPrice * 0.73;
        double reducedPrice = fullPrice;
        final String noVatFormatted = String.format("%.2f", noVAT);
        priceTable.addCell("Nettó ár:");
        priceTable.addCell(noVatFormatted + " Ft");

        priceTable.addCell("Teljes összeg:");
        priceTable.addCell(fullPrice + " Ft");

        priceTable.addCell("Fizetendő összeg:");
        priceTable.addCell(Math.round(reducedPrice) + " Ft");

        priceTable.completeRow();
    }

    public LocalDate getTodaysDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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