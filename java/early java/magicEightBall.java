void p(String s) {
    System.out.println(s);
}

String eightBall(int n) {
    if (n == 0) {
        return "Success!";
    } else if (n == 1) {
        return "Not Likely";
    } else if (n == 2) {
        return "Possibly";
    } else if (n == 3) {
        return "Roll Again";
    }
    return "You Failed";
}

String magicEightBall() {
    return eightBall((int)(Math.random()*5));
}
