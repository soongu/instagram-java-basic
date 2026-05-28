void main() {
    // 해시태그 인기 막대 그래프
    System.out.println("=== 인스타그램 해시태그 인기 차트 ===");
    System.out.println();

    String[] hashtags = {"#맛집  ", "#여행  ", "#일상  ", "#운동  ", "#카페  "};
    int[] counts = {12, 8, 15, 6, 10};

    for (int i = 0; i < hashtags.length; i++) {
        System.out.print(hashtags[i] + " ");

        for (int j = 0; j < counts[i]; j++) {
            System.out.print("█");
        }

        System.out.println(" (" + counts[i] + ")");
    }
    System.out.println();

    // 정규화 차트 (최대 20칸 기준)
    System.out.println("=== 정규화 차트 (최대 20칸) ===");
    System.out.println();

    String[] tags = {"#OOTD      ", "#먹스타그램 ", "#셀카      ", "#풍경      "};
    int[] posts = {350, 120, 280, 90};

    int maxPosts = 0;
    for (int p : posts) {
        if (p > maxPosts) {
            maxPosts = p;
        }
    }

    for (int i = 0; i < tags.length; i++) {
        System.out.print(tags[i] + " ");

        int barLength = posts[i] * 20 / maxPosts;
        for (int j = 0; j < barLength; j++) {
            System.out.print("▓");
        }

        System.out.println(" " + posts[i] + "건");
    }
    System.out.println();

    // 시간대별 활동량
    System.out.println("=== 시간대별 인스타 활동량 ===");
    System.out.println();

    String[] hours = {"06-09시", "09-12시", "12-15시", "15-18시", "18-21시", "21-24시"};
    int[] activity = {3, 7, 5, 8, 14, 11};

    for (int i = 0; i < hours.length; i++) {
        System.out.print(hours[i] + " | ");

        for (int j = 0; j < activity[i]; j++) {
            System.out.print("●");
        }

        System.out.println(" " + activity[i] + "건");
    }
}
