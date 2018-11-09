import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http'
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { MatDialogModule } from "@angular/material";
import { DetailsModalComponent } from './details-modal/details-modal.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    DetailsModalComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    MatDialogModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
