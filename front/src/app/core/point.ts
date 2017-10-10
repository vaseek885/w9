export class Point {
  
  constructor (public x: Number, public y: Number, public r: Number, public included: Boolean) {}
  public static toString( point: Point): string {
    return '?x=' + point.x + '&y=' + point.y + '&r=' + point.r;
  }
 
}
