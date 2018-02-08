export class PointPayload {
    constructor (public x: Number, public y: Number, public r: Number, public included: Boolean, public username: String, public password: String) {}
    public toJson(): String {
        return '{name:\"' + this.username + '\", password:\"' + this.password + '\", x:\"' + this.x + '\", y:\"' + this.y + '\", r:\"' + this.r + '\"}';
    }
}