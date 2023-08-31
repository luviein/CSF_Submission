
import { TagAndCount } from './../../postNews.model';
import { Component, OnInit } from '@angular/core';
import { Tag } from 'kaboom';
import { NewsServerService } from 'src/app/Service/news-server.service';

@Component({
  selector: 'app-view0',
  templateUrl: './view0.component.html',
  styleUrls: ['./view0.component.css']
})
export class View0Component implements OnInit {

  constructor(private svc : NewsServerService) {}
  tagAndCount : TagAndCount[] = []
  async ngOnInit(): Promise<void> {
    try{
      const result = await this.svc.getTag()
      this.tagAndCount = result.map((jsonString: any) => {
        const parsedInfo = JSON.parse(JSON.stringify(jsonString)) as TagAndCount;
        console.log("parsed info >>>>", parsedInfo)
        return parsedInfo});
    }catch (error){
      console.error("printing error: ", error)
    }
  }
  }
