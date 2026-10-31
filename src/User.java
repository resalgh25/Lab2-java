public class User {
    private String email ;
    private String password ;

    public User(String email, String password) {
    if (email.length() > 50) {
        throw new IllegalArgumentException(
            "Username is too long, try something shorter");
    }

    if (!Checkemail(email)) {
        throw new IllegalArgumentException(
            "Please enter a valid Email as username");
    }

    if (password.length() < 8) {
        throw new IllegalArgumentException(
            "Your password is too short, add more characters");
    }

    if (password.length() > 12) {
        throw new IllegalArgumentException(
            "Your password is too long, try a shorter one");
    }

    if (!Checkpassword(password)) {
        throw new IllegalArgumentException(
            "Please enter a valid password");
    }

    this.email = email;
    this.password = password;
}

 private boolean Checkemail( String email){
    if (email.length()>50){
        return false;
    }
    
    int i=0;
    while (i<email.length() && email.charAt(i)!='@'){
      char c = email.charAt(i);
      if ((c>= 65 && c<=90)||(c>=97 && c<=122)||(c>=48 &&c<=57)||(c==46)||(c==95)||(c==37)||(c==43)||(c==45)){
         i++;
      }
      else {
         return false;
      }

    }
    if (i==0 || i==email.length()){
         return false ;
    }
    i++;
    if (i == email.length()) {
        return false;
    }
    char a = email.charAt(i);
    if ((a>= 65 && a<=90)||(a>=97 && a<=122)||(a>=48 && a<=57)){
      i++;
    }
    else{
      return false ;
    }

    int finalDot = email.lastIndexOf('.');
    if (finalDot<i){
      return false ;
    }
    while (i<finalDot){
      char c = email.charAt(i);
      if ((c>= 65 && c<=90)||(c>=97 && c<=122)||(c>=48 &&c<=57)||(c==46)||(c==95)||(c == 45)||(c == 43)){
         i++;
      }
      else {
         return false ;
      }
    }
    i = finalDot + 1;
    if (email.length()-finalDot-1 <2){
      return false ;
    }
    while (i<email.length()){
      char c = email.charAt(i);
      if ((c>= 65 && c<=90)||(c>=97 && c<=122)){
         i++;
      }
      else{
         return false;
      }

    }

    return true ;
 }

 private boolean Checkpassword(String password){
  boolean bigLetter = false ;
  boolean smallLater = false ;
  boolean number = false ;
  boolean sign = false ;

  if (password.length()<8 || password.length()>12){
      return false ;
  }
  int j= 0 ;
  while (j<password.length()){
    char c= password.charAt(j);
    if (c>=65 && c<=90){
      bigLetter= true ;
    }
    else if(c>=97 &&c<=122){
      smallLater= true ;
    }
    else if(c>=48 && c<=57){
      number= true ;
    }
    else if((c==33)||(c==35)||(c==36)||(c==37)||(c==38)||(c==40)||(c==41)||(c==42)||(c==64)||(c==94)){
      sign = true;
    }
    else {
      return false ;
    }
    j++;
  } 
  if ((bigLetter||smallLater)&&number&&sign){
     return true ;
  }
  else{
    return false ;
  }

 }
 @Override
  public String toString() {
     return email + " " + password;
  }
}
