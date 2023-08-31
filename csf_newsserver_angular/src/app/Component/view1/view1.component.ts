import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NewsServerService } from 'src/app/Service/news-server.service';
import { NewsInput } from 'src/app/postNews.model';

@Component({
  selector: 'app-view1',
  templateUrl: './view1.component.html',
  styleUrls: ['./view1.component.css']
})
export class View1Component implements OnInit {

  constructor(private activatedRoute : ActivatedRoute, private router: Router, private svc: NewsServerService){}

  tagName!: string

  news: NewsInput[] = []


  async ngOnInit(): Promise<void> {
    try{
      const result = await this.svc.getTag()
      this.news = result.map((jsonString: any) => {
        const parsedInfo = JSON.parse(JSON.stringify(jsonString)) as NewsInput;
        console.log("parsed info >>>>", parsedInfo)
        return parsedInfo});
    }catch (error){
      console.error("printing error: ", error)
    }
  }

  postNews() {
    this.router.navigate(['postnews'])
  }
}
