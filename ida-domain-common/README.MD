# ida-domain-common
도메인 레이어(모듈)에서 자주 사용되는 클래스 혹은 기능들을 따로 분리하여
관리하는 도메인 공통 모듈입니다.

대체로 애그리거트로 분리되었지만, 연관관계에 의해 가독성이 떨어져
ID 클래스로 서로 연결되어 있음을 확인할 수 있는 클래스 등을 정의할 수 있습니다.
대표적으로 MemberId 클래스가 존재합니다.

ID 클래스 외에도 많은 애그리거트에서 사용되는 Value-Class 들 역시 정의되어 있습니다.
이 역시 가독성을 높이기 위해 따로 클래스화 시킨 것입니다.
대표적으로 PhoneNumber Value-Class 가 존재합니다.

P.S. 아직 해당 모듈은 미성숙한 모듈입니다.
아직 완전히 정의된 부분들이 없기에 여러 번의 리팩토링을 거칠 필요가 있습니다.
