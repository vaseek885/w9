import { Component, OnInit, Input } from '@angular/core';
import { Point } from '../../core/point'

@Component({
  selector: 'app-points-list',
  templateUrl: './points-list.component.html',
  styleUrls: ['./points-list.component.css']
})
export class PointsListComponent implements OnInit {
  
  constructor() { }
  
  @Input() chekedPoints: Point[];
  
  ngOnInit() {
  }

}
