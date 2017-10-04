import {NgModule} from '@angular/core';
import {HttpModule} from '@angular/http';
import {FormsModule} from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';


import {HomeComponent} from './home.component';
import {LogoutComponent} from '../auth/logout/logout.component';
import { CanvasComponent } from './canvas/canvas.component';
import { PointsListComponent } from './points-list/points-list.component';




@NgModule({
    imports: [ BrowserModule,
       HttpModule,
     FormsModule ],
    providers: [ ],
    declarations: [HomeComponent, LogoutComponent, CanvasComponent, PointsListComponent]
})
export class HomeModule {
}
