import { Component, OnInit } from '@angular/core';
import { HttpClientModule } from '@angular/common/http'
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  private books = [];

  constructor(private httpClient: HttpClient) {
    this.get_books()
  }

  get_books(){
    this.httpClient.get('http://localhost:8080/books/all').subscribe((res : any[])=>{
      console.log(res);
      this.books = res;
    });
  }

  ngOnInit() {
  }

}
