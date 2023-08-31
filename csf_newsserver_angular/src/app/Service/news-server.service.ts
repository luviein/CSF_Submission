import { HttpClient } from '@angular/common/http';
import { ElementRef, Injectable } from '@angular/core';
import { postComments } from '../postNews.model';
import {firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NewsServerService {

  constructor(private http: HttpClient) { }
  tagArray : string[] = []

  saveList(tag: any) {
    this.tagArray = tag
  }

  retrieveTag() {
    return this.tagArray
  }
  upload(elemRef: ElementRef, form:any)
  : Promise<any>
  {

    console.info(">>>>> files: ", elemRef.nativeElement.files)
    console.info("form data >>>>>", form)
    const data = new FormData()
    data.append("title", form.title)

    data.append("photo", elemRef.nativeElement.files[0])
    data.append("description",form.description)

    data.append("tags", JSON.stringify(this.tagArray));
    // form.tags.forEach((value: string) => {this.tagArray.push(value)})
    console.log(">>>>> tags in service", JSON.stringify(this.tagArray))

    console.log("data >>>>>>>",data)


    return firstValueFrom(
      this.http.post<any>("http://localhost:8080/upload", data)
    )
  }
}
