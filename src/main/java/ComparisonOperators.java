void main() {
    // 비교 연산자 — 결과는 항상 boolean (true 또는 false)
    int myFollowers = 1500;
    int yourFollowers = 3200;

    // 같은가, 다른가
    System.out.println("같은가? " + (myFollowers == yourFollowers));
    System.out.println("다른가? " + (myFollowers != yourFollowers));

    // 크기 비교
    System.out.println("내가 더 많은가? " + (myFollowers > yourFollowers));
    System.out.println("내가 더 적은가? " + (myFollowers < yourFollowers));
    System.out.println("같거나 많은가? " + (myFollowers >= yourFollowers));
    System.out.println("같거나 적은가? " + (myFollowers <= yourFollowers));

    // 실전 활용 — 인스타그램 인플루언서 기준
    int threshold = 10000;
    boolean isInfluencer = myFollowers >= threshold;
    System.out.println("인플루언서인가? " + isInfluencer);

    // 나이 확인
    int age = 15;
    boolean isAdult = age >= 18;
    System.out.println("성인인가? " + isAdult);

    // 게시물 수 비교
    int postCount = 0;
    boolean hasNoPosts = postCount == 0;
    System.out.println("게시물이 없는가? " + hasNoPosts);
}
