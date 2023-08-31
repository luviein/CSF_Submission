import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-view2',
  templateUrl: './view2.component.html',
  styleUrls: ['./view2.component.css']
})
export class View2Component implements OnInit {

  form!: FormGroup
  @ViewChild("photoUpload")
  toUpload!: ElementRef

  constructor(private fb : FormBuilder) {}

  ngOnInit(): void {
      this.form = this.fb.group({
        title: this.fb.control<string> ("", [Validators.required, Validators.minLength(5)]),
        description: this.fb.control<string> ("", [Validators.required, Validators.minLength(5)]),
        tags: this.fb.control<string> ("")
      })
  }

}
