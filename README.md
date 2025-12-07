# 프로젝트 문서

## 🛠 기술 스택

### Backend

- **Language**: Java 21
- **Framework**: Spring Boot 4.0.0
- **Database**: H2 Database (In-Memory)
- **Persistence**: MyBatis 4.0.0
- **Build Tool**: Maven

### Frontend

- **Framework**: Vue 3 (`npm create vue@latest`)
- **State Management**: Pinia
- **Routing**: Vue Router
- **Linting & Formatting**: ESLint, Prettier

---

## ⚙️ 개발 가이드라인

### 1. 코드 포맷팅

> IntelliJ IDEA의 내장 포맷터를 사용하여 파일을 저장할 때마다 자동으로 코드를 정리합니다.

#### 설정 방법:

1. **Settings** (Windows/Linux) 또는 **Preferences** (macOS)를 엽니다 (`File > Settings` 또는 `IntelliJ IDEA > Settings`).
2. 왼쪽 메뉴에서 **Tools > Actions on Save**를 선택합니다.
3. **Reformat code** 항목을 체크합니다. (본 프로젝트는 특정 파일 타입 지정 X)
4. **Optimize imports** 항목도 체크하여 임포트 문 정리도 동시에 수행하도록 설정합니다.
5. **Apply** 후 **OK**를 눌러 설정을 저장합니다.

### 2. 커밋 컨벤션

> 모든 커밋 메시지는 `type(scope): subject` 형태로 작성합니다.

#### 상세 구조:

- **Type**: 소문자 영어 (예: feat, fix, docs, style, refactor, test, chore)
- **Scope**: (선택사항) 변경 범위를 괄호 안에 명시
- **Separator**: 콜론 후 한 글자 띄어쓰기 (`: `)
- **Subject**: 상세한 커밋 제목
- **Body**: 되도록 생략하고 제목에 내용을 함축적으로 작성
