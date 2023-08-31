import { HttpClient } from '@angular/common/http';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NewsServerService } from 'src/app/Service/news-server.service';
import { postComments } from 'src/app/postNews.model';

@Component({
  selector: 'app-view2',
  templateUrl: './view2.component.html',
  styleUrls: ['./view2.component.css']
})
export class View2Component implements OnInit {

  form!: FormGroup

  @ViewChild("photoUpload")
  toUpload!: ElementRef

  tagsArray: string[] = []
  tag!: string

  constructor(private fb : FormBuilder, private svc: NewsServerService, private router: Router) {}

  ngOnInit(): void {
      this.form = this.fb.group({
        title: this.fb.control<string> ("", [Validators.required, Validators.minLength(5)]),
        description: this.fb.control<string> ("", [Validators.required, Validators.minLength(5)]),
        tags: this.fb.control<string> ("")
      })
  }

  addTag() {
    if (/\s/.test(this.tag)) {
      // It has any kind of whitespace
      let stringToSplit = this.tag;
      let x = stringToSplit.split(" ");
      x.forEach(value => {this.tagsArray.push(value)})
      console.log(x);
    } else {
      this.tagsArray.push(this.tag)
    }
  }

  removeTag(tag: number) {
    this.tagsArray.splice(tag, 1);
  }

  process() {
    // const formData = this.form.value as postComments
    const commentData = {
      title: this.form.value.title,
      description: this.form.value.description,
      tags: this.form.value.tags
    }
    console.log("commentdata >>>>", commentData)
    this.svc.saveList(this.tagsArray)
    this.svc.upload(this.toUpload, commentData).then(resp => {
      alert(JSON.stringify(resp))
      this.router.navigate(['/toptags'])
    }).catch(error => {
      alert(error)
    })
  }

}
