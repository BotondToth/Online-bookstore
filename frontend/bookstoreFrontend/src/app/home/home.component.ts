import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  private books = [];

  private book: any[];

  constructor(private httpClient: HttpClient, private modalService: NgbModal) {
    this.get_books()
  }

  get_books(){
    this.httpClient.get('http://localhost:8080/books/all').subscribe((res : any[])=>{
      this.books = res;
    });
  }

  openDialog(isbn: string, content) {
    this.httpClient.get('http://localhost:8080/books/isbn/'+isbn).subscribe((res : any[])=>{
      this.book = res;
    });
    this.modalService.open(content, {backdropClass: 'light-blue-backdrop'});
  }

  buyItem(isbn: string) {
    console.log(isbn);
  }

  titleSearch() {
    let inputValue = (<HTMLInputElement>document.getElementById('searchBar')).value;
    this.httpClient.get('http://localhost:8080/search/'+inputValue).subscribe((res : any[])=>{
      this.books = res;
    });
  }

  detailedSearch(){
    let a = " ";
    let b = " ";
    let c = "uresmezo";


    console.log((<HTMLInputElement>document.getElementById('searchBar1')).value);

    if((<HTMLInputElement>document.getElementById('searchBar1')).value != ""){
      a = (<HTMLInputElement>document.getElementById('searchBar1')).value;

    }

    if((<HTMLInputElement>document.getElementById('searchBar2')).value != ""){
      b = (<HTMLInputElement>document.getElementById('searchBar2')).value;

    }

    if((<HTMLInputElement>document.getElementById('searchBar3')).value != ""){
      c = (<HTMLInputElement>document.getElementById('searchBar3')).value;

    }

    this.httpClient.get('http://localhost:8080/search/book/'+ a + "/" + b + "/" + c).subscribe((res : any[])=>{
      this.books = res;
    });

  }

  showDetailedSearchForm(){
    document.getElementById("detailedsearch").className = "form-inline";
    document.getElementById("detailedsearch").style.display = "block";
    document.getElementById("detailedsearchbutton").style.display = "none";
  }

  ngOnInit() {
  }

}
