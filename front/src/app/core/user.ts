export class User {
  public constructor (public username: String, public password: String) {  }
  public static fromJson(date: any ): User {
    const jsonData = date.json();
    return new User(jsonData.name, jsonData.password);
  }
  public static isNull(user: User): boolean {
    if (user.username == null || user.password == null || user.username === '' || user.password === '') {
      return true;
    } else {
      return false;
    }
  }
  public toJson(): String {
    return '{name:\"' + this.username + '\", password:\"' + this.password + '\"}';
  }
  
}
