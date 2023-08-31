import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router'
import {HttpClientModule} from '@angular/common/http';
import { View2Component } from './Component/view2/view2.component';

import { View0Component } from './Component/view0/view0.component';
import { View1Component } from './Component/view1/view1.component';
import { NewsServerService } from './Service/news-server.service';

const appRoutes : Routes = [
  {path: "", component:View0Component, title:"Top News"},
  {path: "postnews", component:View2Component, title:"Share A News"},
  {path: "news/:id", component:View1Component, title:"News"}

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
