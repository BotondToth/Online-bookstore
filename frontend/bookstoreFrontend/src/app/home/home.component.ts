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

  ngOnInit() {
  }

}
