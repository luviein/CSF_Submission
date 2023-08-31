import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router'
import {HttpClientModule} from '@angular/common/http';
import { View2Component } from './Component/view2/view2.component';
import { NewsServerService } from './Service/news-server.service';
import { View0Component } from './Component/view0/view0.component';
import { View1Component } from './Component/view1/view1.component';

const appRoutes : Routes = [
  {path: "", component:View2Component, title:"Share A News"},
  {path: "toptags", component:View0Component, title:"Top Tags"},

]



@NgModule({
  declarations: [
    AppComponent,
    View2Component,
    View0Component,
    View1Component
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes, {useHash: true})

  ],
  providers: [NewsServerService],
  bootstrap: [AppComponent]
})
export class AppModule { }
