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

  private user: any[];

  private basket: any[];
  private username: string;

  private loggedIn: boolean;

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

  titleSearch() {
    let inputValue = (<HTMLInputElement>document.getElementById('searchBar')).value;
    if(inputValue=="")this.get_books() ;
    else{
      this.httpClient.get('http://localhost:8080/search/'+inputValue).subscribe((res : any[])=>{
        this.books = res;
      });}
    }
  }

  loginPanel(content) {
    this.modalService.open(content, {backdropClass: 'light-blue-backdrop'});
  }

  openRegisterModal(content) {
    this.modalService.open(content, {backdropClass: 'light-blue-backdrop'});
  }

  login() {
    let userNameInput = (<HTMLInputElement>document.getElementById('userNameInput')).value;
    let passwordInput = (<HTMLInputElement>document.getElementById('passwordInput')).value;
    var data = {};
    data['email'] = userNameInput;
    data['password'] = passwordInput;
    this.httpClient.post('http://localhost:8080/user/login', data, { responseType: 'text' }).subscribe((res : string)=>{
        if (res=="") {
          alert('Rossz email cím vagy jelszó!');
        } else {
          this.loggedIn = true;
          this.username = userNameInput;
          this.basket = new Array();
          var hiddenElemens = document.getElementById("logoutBtn");
          hiddenElemens.style.display = "block";
          hiddenElemens = document.getElementById("loginBtn");
          hiddenElemens.style.display = "none";
          hiddenElemens = document.getElementById("basketBtn");
          hiddenElemens.style.display = "block";
          //login panel eltunjon
        }
    });

  }

  addToBasket(isbn: string) {
    if (this.loggedIn == true) {
      this.httpClient.get('http://localhost:8080/sale/isbn/'+isbn,  { responseType: 'text' }).subscribe((res : string)=>{
        if (res === "Added") {
          alert('A termék hozzáadva a kosárhoz!');
        }
      });
    } else {
      alert('A vásárláshoz jelentkezzen be!');
    }
  }

  getBasketElements(){
    this.httpClient.get('http://localhost:8080/sale/getbooks').subscribe((res : any[])=>{
      this.basket = res;
    });
  }

  openBasket(content) {
    this.getBasketElements();
    this.modalService.open(content, {backdropClass: 'light-blue-backdrop'});
  }

  checkout() {
    if (this.loggedIn == true) {
      this.httpClient.get('http://localhost:8080/sale/add/'+this.username ,  { responseType: 'text' }).subscribe((res : string)=>{
        if(res == "Ok"){
          alert("Köszönjük a vásárlást");
        }
      });
    }

  }

  logout() {
    this.loggedIn = false;
    this.username = "";
    var hiddenElemens = document.getElementById("logoutBtn");
    hiddenElemens.style.display = "none";
    hiddenElemens = document.getElementById("loginBtn");
    hiddenElemens.style.display = "block";
    hiddenElemens = document.getElementById("basketBtn");
    hiddenElemens.style.display = "none";
  }

  register() {
    let firstNameInput = (<HTMLInputElement>document.getElementById('firstNameInput')).value;
    let lastNameInput = (<HTMLInputElement>document.getElementById('lastNameInput')).value;
    let passwordInput = (<HTMLInputElement>document.getElementById('registerPasswordInput')).value;
    let passwordAgainInput = (<HTMLInputElement>document.getElementById('registerPasswordInputAgain')).value;
    let emailInput = (<HTMLInputElement>document.getElementById('registerInputEmail')).value;
    var data = {};
    data['email'] = emailInput;
    data['password'] = passwordInput;
    data['firstName'] = firstNameInput;
    data['lastName'] = lastNameInput;
    if (passwordInput == passwordAgainInput) {
      this.httpClient.post('http://localhost:8080/user/register',data, { responseType: 'text' }).subscribe((res : string)=>{
        console.log(res);
      });
    } else {
      alert('Jelszó nem egyezik!')
    }
  }

  detailedSearch(){
      let a = " ";
      let b = " ";
      let c = "uresmezo";

      if((<HTMLInputElement>document.getElementById('searchBar1')).value != ""){
        a = (<HTMLInputElement>document.getElementById('searchBar1')).value;
      }

      if((<HTMLInputElement>document.getElementById('searchBar2')).value != ""){
        b = (<HTMLInputElement>document.getElementById('searchBar2')).value;
      }

      if((<HTMLInputElement>document.getElementById('searchBar3')).value != ""){
        c = (<HTMLInputElement>document.getElementById('searchBar3')).value;
      }
      if(a==" " && b == " " && c == "uresmezo")this.get_books(); else{
      this.httpClient.get('http://localhost:8080/search/book/'+ a + "/" + b + "/" + c).subscribe((res : any[])=>{
        this.books = res;
      });}

    }

    showDetailedSearchForm(){
      document.getElementById("detailedsearch").className = "form-inline";
      document.getElementById("detailedsearch").style.display = "block";
      document.getElementById("detailedsearchbutton").style.display = "none";
    }


  ngOnInit() {
  }

}
