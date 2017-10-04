export class Point {
  
  constructor (public x: Number, public y: Number, public r: Number, public inAria: String) {}
  public static toString( point: Point): string {
    return '?x=' + point.x + '&y=' + point.y + '&r=' + point.r;
  }
 
}
