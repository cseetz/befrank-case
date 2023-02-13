import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HttpClientModule} from '@angular/common/http';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';

import {FormsModule} from "@angular/forms";
import {MaterialModule} from "./added-modules/material.module";

import {NavbarComponent} from './components/navbar/navbar.component';
import {DashboardComponent} from './components/dashboard/dashboard.component';
import {OverzichtComponent} from './components/dashboard/overzicht/overzicht.component';
import {PlannerComponent} from './components/dashboard/planner/planner.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    DashboardComponent,
    PlannerComponent,
    OverzichtComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    MaterialModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
