package com.instagram.javabasic.modern;

// com/instagram/javabasic/modern/TextBlockPractice.java
// 실무에서 텍스트 블록이 가장 빛나는 세 자리예요: SQL 쿼리·JSON·HTML.
// 셋 다 "여러 줄에 들여쓰기가 있는 긴 글" 이라, 텍스트 블록으로 적으면 원본 모양 그대로 읽혀요.
// (여기서 SQL 은 그냥 문자열일 뿐이에요 — 실제 데이터베이스에 연결하지 않아요.)
public class TextBlockPractice {

    public static void main(String[] args) {
        // 1) SQL 쿼리 — 코드 안에 들여써도, 가장 왼쪽 글자를 기준으로 공통 들여쓰기는 잘려나가요.
        String sql = """
                SELECT username, followers
                FROM member
                WHERE followers >= 1000
                ORDER BY followers DESC""";
        System.out.println("=== SQL ===");
        System.out.println(sql);

        // 2) JSON 구조 — 중괄호와 들여쓰기가 그대로 보여서, 데이터 모양을 눈으로 확인하기 좋아요.
        String json = """
                {
                  "username": "jaehoon",
                  "followers": 1240,
                  "posts": 42
                }""";
        System.out.println("=== JSON ===");
        System.out.println(json);

        // 3) HTML 마크업 — 화면에 그릴 카드 한 조각도 모양 그대로 담을 수 있어요.
        String html = """
                <div class="profile">
                  <h2>@jaehoon</h2>
                  <p>팔로워 1240명</p>
                </div>""";
        System.out.println("=== HTML ===");
        System.out.println(html);

        // 들여쓰기 자동 정렬 확인 — 코드에선 16칸 들여썼지만,
        // 실제 문자열의 각 줄은 공통 들여쓰기가 잘려 0칸/2칸부터 시작해요.
        String firstLine = sql.lines().findFirst().orElse("");
        System.out.println("SQL 첫 줄 맨 앞 공백 수: "
                + (firstLine.length() - firstLine.stripLeading().length()) + "칸");
    }
}
