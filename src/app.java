public class app {
  static checker test = new checker();

  public static void main(String[] args) {

    // System.out.println(test.isProposition("try45454dasdasd5"));

    // System.out.println(test.formula.toString());
    String formula = "(!!(P se, somente se Q) + R <-> S))";
    System.out.println(test.Converter(formula, 0) + "  " + test.simpleValidation(formula));
    System.out.println(test.formula.toString());

    // System.out.println(function("+asdasd"));
    // System.out.println(function("asdasd"));
  }

  public static String function(String str) {
    int count = 0;
    if (!test.isConective(str.charAt(0) + "")) {
      char[] ArrayChar = str.toCharArray();
      for (char char1 : ArrayChar) {
        if (!Character.isLetter(char1))
          if (count == 0 && Character.isDigit(char1)) {
            return "";
          } else if (!Character.isDigit(char1)) {
            return "" + str.substring(0, count);
          }
        count++;
      }
      return str.substring(0, count);
    }
    return str.substring(0, count);
  }
}
