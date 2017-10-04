import { Component, OnInit, ViewChild, ElementRef, Input, OnDestroy } from '@angular/core';
import { Point } from '../../core/point';
import { AuthService } from '../../auth/service/auth.service';
import { R_CHOISES, X_CHOISES, Y_CHOISES } from '../static/choses';

@Component({
  selector: 'app-canvas',
  templateUrl: './canvas.component.html',
  styleUrls: ['./canvas.component.css']
})
export class CanvasComponent implements OnInit, OnDestroy {
  
  
  constructor(private authservice: AuthService) { }
  @ViewChild('myCanvas') canvasRef: ElementRef;
  cont: CanvasRenderingContext2D ;
  rValues = R_CHOISES;
  xValues = X_CHOISES;
  yValues = Y_CHOISES;
  points: Point[];
  w = 540;
  h = 540;
  r = 1;
  x = 1;
  y = 1;
  scaleX = (this.w - 40) / (this.r * 2);
  scaleY = (this.h - 40) / (this.r * 2);
  
  ngOnInit() {
    console.log('canvas init');
    this.authservice.getR().then(response => {
      this.r = response.json(); 
      this.updateScales();
      this.cont = this.canvasRef.nativeElement.getContext('2d');
    this.cont.translate(this.w / 2, this.h / 2);
    this.init();
    this.drawPointsOnInit();
    }).catch(() => {this.r = 1; 
    this.updateScales();
      this.cont = this.canvasRef.nativeElement.getContext('2d');
    this.cont.translate(this.w / 2, this.h / 2);
    this.init();
    this.drawPointsOnInit(); });
    console.log(this.r);    
  }
  ngOnDestroy() {
    console.log('destroy canvas');
  }
  onSumbit(): void {
    this.authservice.addPoint(new Point(this.x, this.y, this.r, null)).then(response => this.drawPoint(response.json() as Point));
  }
  
  deleteAll(): void {
    this.authservice.deleteAll().then(() => {
      this.r = 1;
      this.updateScales();
      this.init();
     });
  }
  
  clicked(event): void {
     console.log(event.offsetX);
     console.log(event.offsetY);
     const point: Point = new Point((event.offsetX - this.w / 2) / this.scaleX, (-event.offsetY + this.w / 2) / this.scaleY, this.r, '');
     this.authservice.addPoint(point).then((response) => {
      this.drawPoint(response.json() as Point); 
     })
  }
  rChange(): void {
    console.log('change');
    this.authservice.updatePoint(this.r).then((response) => {
      this.updateScales();
      this.init();
      this.drawPointsOnInit(); 
     })
  }
  
  getR(): void {
    this.authservice.getR().then(response => {
      this.r = response.json(); 
      this.updateScales();
    }).catch(() => {this.r = 1; });
    console.log(this.r);
  }
  updateScales(): void {
    this.scaleX = (this.w - 40) / (this.r * 2);
    this.scaleY = (this.h - 40) / (this.r * 2);
  }
  
  drawPoint(point: Point): void {
    if (point.inAria) {
      this.cont.fillStyle = '#00ff00';
    } else {
      this.cont.fillStyle = 'red';
    }
    console.log('draw');
    this.cont.beginPath();
    this.cont.arc(point.x as number * this.scaleX , - point.y as number * this.scaleY , 3, 0, 2 * Math.PI);
    this.cont.fill();
    this.points.push(point);
    }
   
   drawPointsOnInit(): void {
    this.authservice.getPoint().then(response => {
      for (const point of response.json() as Point[]) {
        console.log(point);
        this.drawPoint(point);
      }
    });
   }
  errorhandle(): void {
  
  }
  
  
  init(): void {
    this.points = [];
    
    this.cont.clearRect(-270, -270, 540, 540);
      // Area
    this.cont.strokeStyle = 'blue';
    this.cont.fillStyle = 'blue';
    
    this.cont.beginPath();
    this.cont.moveTo(0, 0);
    this.cont.lineTo(0, -this.r * this.scaleY);
    this.cont.lineTo(this.r * this.scaleX / 2, -this.r * this.scaleY);
    this.cont.lineTo(this.r * this.scaleX / 2, 0);
    this.cont.lineTo(0, this.r * this.scaleY);
     this.cont.arc(0, 0, this.r * this.scaleX, Math.PI / 2, Math.PI);
     this.cont.lineTo(0, 0);
    this.cont.fill();
    // Axis
    this.cont.strokeStyle = 'black';
    this.cont.fillStyle = 'black';
    // Axis lines
    this.cont.beginPath();
    this.cont.moveTo(-this.w / 2, 0);
    this.cont.lineTo(this.w / 2, 0);
    this.cont.moveTo(0 , -this.h / 2);
    this.cont.lineTo(0 , this.h / 2);
    this.cont.stroke();
    // Axis names
    this.cont.font = '18px Arial';
    this.cont.fillText('R/2', 10, this.r * this.scaleY / 2 + 8);
    this.cont.fillText('R/2', -this.r * this.scaleX / 2 - 14, 0 - 8);
    this.cont.fillText('R/2', 10, -this.r * this.scaleY / 2 + 8);
    this.cont.fillText('R/2', this.r * this.scaleX / 2 - 14, -8);
    this.cont.fillText('R', 10, -this.r * this.scaleY + 8);
    this.cont.fillText('R', -this.r * this.scaleX - 8, -8);
    this.cont.fillText('R', this.r * this.scaleX - 8, -8);
    this.cont.fillText('R', 10, this.r * this.scaleY + 8);
    }    
}

