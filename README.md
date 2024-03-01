중고거래 게시판
***

_프로젝트를 통해 지금까지 학습한 내용들을 전체적으로 복습하고 각 핵심 개념을 내재화
테마를 직접 정하고 기본적인 게시판 페이지를 기반으로 좀 더 차별화된 컨셉의 게시판을 커스터마이징
스프링 프레임워크의 핵심 원리를 이해하고 적용

***
<h1>상세내용</h1>
***
- 목적 : 중고물품을 거래하기 위해 게시글을 올리고 댓글로 거래를 요청하는 게시판 플랫폼을 구현

- 목표 : 
  - 게시판 메인 페이지에서 여러 카테고리 별로, 글 주제를 확인
      - 회원 가입을 하지 않아도 게시글을 확인하고, 댓글을 작성할 수 있다.
      - 최소한의 클릭으로 게시판을 빠르게 작성할 수 있다.

- 페르소나 : 
	- 당근(26) : "자취방에 있는 중고물품 판매합니다"
	- 직업 : 직장인
	- 거주 : 서울
	- 캐릭터 : 중고물품 거래를 자주함

- 상세기능 : 

	1. 게시판 도메인 
		-구현기능
		- 게시판 생성 : 사용자가 게시판을 생성할 수 있다.
		- 게시판 조회 : 사용자가 게시판을 조회할 수 있다.
		- 게시판 수정 : 게시판 작성자는 작성한 게시판을 수정할 수 있다.
		- 게시판 삭제 : 게시판 작성자는 작성한 게시판을 삭제할 수 있다.
		-구현 페이지
		-  게시판 목록 - 게시판 목록 나열된 페이지
		- 게시판 생성 - 게시판을 생성하는 페이지
		- 게시판 수정 - 게시판을 수정하는 페이지
		- 게시판 - 게시판 메인 페이지
	2. 게시글 도메인
		-구현 기능
		- 게시판 생성 : 사용자가 게시글을 생성할 수 있다.
		- 게시판 조회 : 사용자가 게시글을 조회할 수 있다.
		- 게시판 수정 : 게시글 작성자는 작성한 게시글을 수정할 수 있다.
		- 게시판 삭제 : 게시글 작성자는 작성한 게시글을 삭제할 수 있다.
	3. 댓글 도메인 
		-구현 기능
		- 댓글 등록 - 사용자는 게시글에 댓글을 등록할 수 있다.
		- 댓글 조회 - 사용자는 댓글을 조회 할 수 있다.
		- 댓글 수정 - 댓글 작성자는 작성한 댓글을 수정할 수 있다.
		- 댓글 삭제 - 댓글 작성자는 작성한 댓글을 삭제할 수 있다.
	4. 중고거래유무
		-구현 기능
		- 거래가능 유무 : 게시글에 거래가능, 거래중, 거래 종료를 등록할 수 있다.
	5. ERD 초안 
	6. 와이어프레임