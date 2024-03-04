package com.example.finalproject

object TrainIdNmList {

    /*
    역조회시 참조: 응암 -> 응암순환(상선), 공릉 -> 공릉(서울산업대입구), 춘의 ->
    춘의, 남한산성입구 ->
    남한산성입구(성남법원, 검찰청), 대모산입구 -> 대모산, 천호 -> 천호(풍납토성), 몽촌토성 -> 몽촌토성(평화의문)
    * 서울시 이외의 역구간은 미제공 됩니다.(예, 광명, 서동탄, 춘천 등)

    * 주의사항 : 원천에서 데이터가 수집 및 가공되어 서비스되는 과정에서 시간 차가 발생할 수 있습니다.
    출력값 중 recptnDt(열차 도착정보를 생성한 시각)는 데이터가 생성된 시간을 의미하며 현재시각과 recptnDt의 차이 만큼 열차가 더 진행한 것으로 보정해서 사용해야 합니다.
    예시) 현재시간이 10시 5분 30초이고, recptnDt가 10시 3분 30초인경우 2분간의 시차가 발생하므로 도착정보는 2분씩 당겨지거나 1개의 역을 더 진행한것으로 판단


     */
    val TrainList = mutableMapOf<Int, String>(
        1001000101 to "동두천", 1001000102 to "보산", 1001000103 to "동두천중앙", 1001000104 to "지행",
        1001000105 to "덕정", 1001000106 to "덕계", 1001000107 to "양주", 1001000108 to "녹양",
        1001000109 to "가능", 1001000110 to "의정부", 1001000111 to "회룡", 1001000112 to "망월사",
        1001000113 to "도봉산", 1001000114 to "도봉", 1001000115 to "방학", 1001000116 to "창동", 1001000117 to "녹천",
        1001000118 to "월계", 1001000119 to "광운대", 1001000120 to "석계", 1001000121 to "신이문", 1001000122 to "외대앞",
        1001000123 to "회기", 1001000124 to "청량리", 1001000125 to "제기동", 1001000126 to "신설동", 1001000127 to "동묘앞",
        1001000128 to "동대문", 1001000129 to "종로5가", 1001000130 to "종로3가", 1001000131 to "종각",
        1001000132 to "시청", 1001000133 to "서울", 1001000134 to "남영", 1001000135 to "용산", 1001000136 to "노량진", 1001000137 to "대방", 1001000138 to "신길",
        1001000139 to "영등포", 1001000140 to "신도림", 1001000141 to "구로", 1001000142 to "구일",
        1001000143 to "개봉", 1001000144 to "오류동", 1001000145 to "온수", 1001000146 to "역곡",
        1001000147 to "소사", 1001000148 to "부천", 1001000149 to "중동", 1001000150 to "송내",
        1001000151 to "부개", 1001000152 to "부평", 1001000153 to "백운", 1001000154 to "동암",
        1001000155 to "간석", 1001000156 to "주안", 1001000157 to "도화", 1001000158 to "제물포",
        1001000159 to "도원", 1001000160 to "동인천", 1001000161 to "인천", 1001075410 to "광명",
        1001080142 to "가산디지털단지", 1001080143 to "독산", 1001080144 to "금천구청", 1001080145 to "석수",
        1001080146 to "관악", 1001080147 to "안양", 1001080148 to "명학", 1001080149 to "금정",
        1001080150 to "군포", 1001080151 to "당정", 1001080152 to "의왕", 1001080153 to "성균관대",
        1001080154 to "화서", 1001080155 to "수원", 1001080156 to "세류", 1001080157 to "병점",
        1001080158 to "세마", 1001080159 to "오산대", 1001080160 to "오산", 1001080161 to "진위",
        1001080162 to "송탄", 1001080163 to "서정리", 1001080164 to "지제", 1001080165 to "평택",
        1001080166 to "성환", 1001080167 to "직산", 1001080168 to "두정", 1001080169 to "천안",
        1001080170 to "봉명", 1001080171 to "쌍용(나사렛대)", 1001080172 to "아산", 1001080173 to "탕정",
        1001080174 to "배방", 1001080175 to "온양온천", 1001080176 to "신창", 1001801571 to "서동탄",
        1002000201 to "시청", 1002000202 to "을지로입구", 1002000203 to "을지로3가", 1002000204 to "을지로4가",
        1002000205 to "동대문역사문화공원", 1002000206 to "신당", 1002000207 to "상왕십리", 1002000208 to "왕십리",
        1002000209 to "한양대", 1002000210 to "뚝섬", 1002000211 to "성수", 1002000212 to "건대입구",
        1002000213 to "구의", 1002000214 to "강변", 1002000215 to "잠실나루", 1002000216 to "잠실",
        1002000217 to "잠실새내", 1002000218 to "종합운동장", 1002000219 to "삼성", 1002000220 to "선릉",
        1002000221 to "역삼",
        1002000222 to "강남",
        1002000223 to "교대",
        1002000224 to "서초",
        1002000225 to "방배",
        1002000226 to "사당",
        1002000227 to "낙성대",
        1002000228 to "서울대입구",
        1002000229 to "봉천",
        1002000230 to "신림",
        1002000231 to "신대방",
        1002000232 to "구로디지털단지",
        1002000233 to "대림",
        1002000234 to "신도림",
        1002000235 to "문래",
        1002000236 to "영등포구청",
        1002000237 to "당산",
        1002000238 to "합정",
        1002000239 to "홍대입구",
        1002000240 to "신촌",
        1002000241 to "이대",
        1002000242 to "아현",
        1002000243 to "충정로",
        1002002111 to "용답",
        1002002112 to "신답",
        1002002113 to "용두",
        1002002114 to "신설동",
        1002002341 to "도림천",
        1002002342 to "양천구청",
        1002002343 to "신정네거리",
        1002002344 to "까치산",
        1003000309 to "대화",
        1003000310 to "주엽",
        1003000311 to "정발산",
        1003000312 to "마두",
        1003000313 to "백석",
        1003000314 to "대곡",
        1003000315 to "화정",
        1003000316 to "원당",
        1003000317 to "원흥",
        1003000318 to "삼송",
        1003000319 to "지축",
        1003000320 to "구파발",
        1003000321 to "연신내",
        1003000322 to "불광",
        1003000323 to "녹번",
        1003000324 to "홍제",
        1003000325 to "무악재",
        1003000326 to "독립문",
        1003000327 to "경복궁",
        1003000328 to "안국",
        1003000329 to "종로3가",
        1003000330 to "을지로3가",
        1003000331 to "충무로",
        1003000332 to "동대입구",
        1003000333 to "약수",
        1003000334 to "금호",
        1003000335 to "옥수",
        1003000336 to "압구정",
        1003000337 to "신사",
        1003000338 to "잠원",
        1003000339 to "고속터미널",
        1003000340 to "교대",
        1003000341 to "남부터미널",
        1003000342 to "양재",
        1003000343 to "매봉",
        1003000344 to "도곡",
        1003000345 to "대치",
        1003000346 to "학여울",
        1003000347 to "대청",
        1003000348 to "일원",
        1003000349 to "수서",
        1003000350 to "가락시장",
        1003000351 to "경찰병원",
        1003000352 to "오금",
        1004000409 to "당고개",
        1004000410 to "상계",
        1004000411 to "노원",
        1004000412 to "창동",
        1004000413 to "쌍문",
        1004000414 to "수유",
        1004000415 to "미아",
        1004000416 to "미아사거리",
        1004000417 to "길음",
        1004000418 to "성신여대입구",
        1004000419 to "한성대입구",
        1004000420 to "혜화",
        1004000421 to "동대문",
        1004000422 to "동대문역사문화공원",
        1004000423 to "충무로",
        1004000424 to "명동",
        1004000425 to "회현",
        1004000426 to "서울",
        1004000427 to "숙대입구",
        1004000428 to "삼각지",
        1004000429 to "신용산",
        1004000430 to "이촌",
        1004000431 to "동작",
        1004000432 to "총신대입구(이수)",
        1004000433 to "사당",
        1004000434 to "남태령",
        1004000435 to "선바위",
        1004000436 to "경마공원",
        1004000437 to "대공원",
        1004000438 to "과천",
        1004000439 to "정부과천청사",
        1004000440 to "인덕원",
        1004000441 to "평촌",
        1004000442 to "범계",
        1004000443 to "금정",
        1004000444 to "산본",
        1004000445 to "수리산",
        1004000446 to "대야미",
        1004000447 to "반월",
        1004000448 to "상록수",
        1004000449 to "한대앞",
        1004000450 to "중앙",
        1004000451 to "고잔",
        1004000452 to "초지",
        1004000453 to "안산",
        1004000454 to "신길온천",
        1004000455 to "정왕",
        1004000456 to "오이도",
        1005000510 to "방화",
        1005000511 to "개화산",
        1005000512 to "김포공항",
        1005000513 to "송정",
        1005000514 to "마곡",
        1005000515 to "발산",
        1005000516 to "우장산",
        1005000517 to "화곡",
        1005000518 to "까치산",
        1005000519 to "신정(은행정)",
        1005000520 to "목동",
        1005000521 to "오목교(목동운동장앞)",
        1005000522 to "양평",
        1005000523 to "영등포구청",
        1005000524 to "영등포시장",
        1005000525 to "신길",
        1005000526 to "여의도",
        1005000527 to "여의나루",
        1005000528 to "마포",
        1005000529 to "공덕",
        1005000530 to "애오개",
        1005000531 to "충정로",
        1005000532 to "서대문",
        1005000533 to "광화문",
        1005000534 to "종로3가",
        1005000535 to "을지로4가",
        1005000536 to "동대문역사문화공원",
        1005000537 to "청구",
        1005000538 to "신금호",
        1005000539 to "행당",
        1005000540 to "왕십리",
        1005000541 to "마장",
        1005000542 to "답십리",
        1005000543 to "장한평",
        1005000544 to "군자(능동)",
        1005000545 to "아차산(어린이대공원후문)",
        1005000546 to "광나루(장신대)",
        1005000547 to "천호(풍납토성)",
        1005000548 to "강동",
        1005000549 to "길동",
        1005000550 to "굽은다리(강동구민회관앞)",
        1005000551 to "명일",
        1005000552 to "고덕",
        1005000553 to "상일동",
        1005000554 to "강일",
        1005000555 to "미사",
        1005000556 to "하남풍산",
        1005000557 to "하남시청",
        1005000558 to "하남검단산",
        1005080549 to "둔촌동",
        1005080550 to "올림픽공원(한국체대)",
        1005080551 to "방이",
        1005080552 to "오금",
        1005080553 to "개롱",
        1005080554 to "거여",
        1005080555 to "마천",
        1006000610 to "응암", // 원래는 응암순환(상선)임. 그런데 실제 역 이름은 응암
        1006000611 to "역촌",
        1006000612 to "불광",
        1006000613 to "독바위",
        1006000614 to "연신내",
        1006000615 to "구산",
        1006000616 to "새절(신사)",
        1006000617 to "증산(명지대앞)",
        1006000618 to "디지털미디어시티",
        1006000619 to "월드컵경기장(성산)",
        1006000620 to "마포구청",
        1006000621 to "망원",
        1006000622 to "합정",
        1006000623 to "상수",
        1006000624 to "광흥창",
        1006000625 to "대흥(서강대앞)",
        1006000626 to "공덕",
        1006000627 to "효창공원앞",
        1006000628 to "삼각지",
        1006000629 to "녹사평",
        1006000630 to "이태원",
        1006000631 to "한강진",
        1006000632 to "버티고개",
        1006000633 to "약수",
        1006000634 to "청구",
        1006000635 to "신당",
        1006000636 to "동묘앞",
        1006000637 to "창신",
        1006000638 to "보문",
        1006000639 to "안암(고대병원앞)",
        1006000640 to "고려대",
        1006000641 to "월곡(동덕여대)",
        1006000642 to "상월곡(한국과학기술연구원)",
        1006000643 to "돌곶이",
        1006000644 to "석계",
        1006000645 to "태릉입구",
        1006000646 to "화랑대(서울여대입구)",
        1006000647 to "봉화산",
        1006000648 to "신내",
        1007000709 to "장암",
        1007000710 to "도봉산",
        1007000711 to "수락산",
        1007000712 to "마들",
        1007000713 to "노원",
        1007000714 to "중계",
        1007000715 to "하계",
        1007000716 to "공릉(서울산업대입구)",
        1007000717 to "태릉입구",
        1007000718 to "먹골",
        1007000719 to "중화",
        1007000720 to "상봉",
        1007000721 to "면목",
        1007000722 to "사가정",
        1007000723 to "용마산",
        1007000724 to "중곡",
        1007000725 to "군자(능동)",
        1007000726 to "어린이대공원(세종대)",
        1007000727 to "건대입구",
        1007000728 to "뚝섬유원지",
        1007000729 to "청담",
        1007000730 to "강남구청",
        1007000731 to "학동",
        1007000732 to "논현",
        1007000733 to "반포",
        1007000734 to "고속터미널",
        1007000735 to "내방",
        1007000736 to "총신대입구(이수)",
        1007000737 to "남성",
        1007000738 to "숭실대입구(살피재)",
        1007000739 to "상도(중앙대앞)",
        1007000740 to "장승배기",
        1007000741 to "신대방삼거리",
        1007000742 to "보라매",
        1007000743 to "신풍",
        1007000744 to "대림",
        1007000745 to "남구로",
        1007000746 to "가산디지털단지",
        1007000747 to "철산",
        1007000748 to "광명사거리",
        1007000749 to "천왕",
        1007000750 to "온수",
        1007000751 to "까치울",
        1007000752 to "부천종합운동장",
        1007000753 to "춘의",
        1007000754 to "신중동",
        1007000755 to "부천시청",
        1007000756 to "상동",
        1007000757 to "삼산체육관",
        1007000758 to "굴포천",
        1007000759 to "부평구청",
        1007000760 to "산곡",
        1007000761 to "석남",
        1008000810 to "암사",
        1008000811 to "천호(풍납토성)",
        1008000812 to "강동구청",
        1008000813 to "몽촌토성(평화의문)",
        1008000814 to "잠실",
        1008000815 to "석촌",
        1008000816 to "송파",
        1008000817 to "가락시장",
        1008000818 to "문정",
        1008000819 to "장지",
        1008000820 to "복정",
        1008000821 to "남위례",
        1008000822 to "산성",
        1008000823 to "남한산성입구(성남법원,검찰청)",
        1008000824 to "단대오거리",
        1008000825 to "신흥",
        1008000826 to "수진",
        1008000827 to "모란",
        1009000901 to "개화",
        1009000902 to "김포공항",
        1009000903 to "공항시장",
        1009000904 to "신방화",
        1009000905 to "마곡나루",
        1009000906 to "양천향교",
        1009000907 to "가양",
        1009000908 to "증미",
        1009000909 to "등촌",
        1009000910 to "염창",
        1009000911 to "신목동",
        1009000912 to "선유도",
        1009000913 to "당산",
        1009000914 to "국회의사당",
        1009000915 to "여의도",
        1009000916 to "샛강",
        1009000917 to "노량진",
        1009000918 to "노들",
        1009000919 to "흑석",
        1009000920 to "동작",
        1009000921 to "구반포",
        1009000922 to "신반포",
        1009000923 to "고속터미널",
        1009000924 to "사평",
        1009000925 to "신논현",
        1009000926 to "언주",
        1009000927 to "선정릉",
        1009000928 to "삼성중앙",
        1009000929 to "봉은사",
        1009000930 to "종합운동장",
        1009000931 to "삼전",
        1009000932 to "석촌고분",
        1009000933 to "석촌",
        1009000934 to "송파나루",
        1009000935 to "한성백제",
        1009000936 to "올림픽공원",
        1009000937 to "둔촌오륜",
        1009000938 to "중앙보훈병원",
        1063075110 to "용산",
        1063075111 to "이촌",
        1063075112 to "서빙고",
        1063075113 to "한남",
        1063075114 to "옥수",
        1063075115 to "응봉",
        1063075116 to "왕십리",
        1063075117 to "청량리",
        1063075118 to "회기",
        1063075119 to "중랑",
        1063075120 to "상봉",
        1063075121 to "망우",
        1063075122 to "양원",
        1063075123 to "구리",
        1063075124 to "도농",
        1063075125 to "양정",
        1063075126 to "덕소",
        1063075127 to "도심",
        1063075128 to "팔당",
        1063075129 to "운길산",
        1063075130 to "양수",
        1063075131 to "신원",
        1063075132 to "국수",
        1063075133 to "아신",
        1063075134 to "오빈",
        1063075135 to "양평",
        1063075136 to "원덕",
        1063075137 to "용문",
        1063075138 to "지평",
        1063075312 to "공덕",
        1063075313 to "서강대",
        1063075314 to "홍대입구",
        1063075315 to "가좌",
        1063075316 to "디지털미디어시티",
        1063075317 to "수색",
        1063075318 to "화전",
        1063075319 to "강매",
        1063075320 to "행신",
        1063075321 to "능곡",
        1063075322 to "대곡",
        1063075323 to "곡산",
        1063075324 to "백마",
        1063075325 to "풍산",
        1063075326 to "일산",
        1063075327 to "탄현",
        1063075328 to "야당",
        1063075329 to "운정",
        1063075330 to "금릉",
        1063075331 to "금촌",
        1063075333 to "월롱",
        1063075334 to "파주",
        1063075335 to "문산",
        1063075826 to "효창공원앞",
        1063080312 to "신촌(경의중앙선)",
        1063080313 to "서울",
        1065006501 to "서울",
        1065006502 to "공덕",
        1065006503 to "홍대입구",
        1065006504 to "디지털미디어시티",
        1065006505 to "김포공항",
        1065006506 to "계양",
        1065006507 to "검암",
        1065006508 to "운서",
        1065006509 to "공항화물청사",
        1065006510 to "인천공항1터미널",
        1065006511 to "인천공항2터미널",
        1065065042 to "마곡나루",
        1065065071 to "청라국제도시",
        1065065072 to "영종",
        1067080116 to "청량리",
        1067080117 to "회기",
        1067080118 to "중랑",
        1067080119 to "광운대",
        1067080120 to "상봉",
        1067080121 to "망우",
        1067080122 to "신내",
        1067080123 to "갈매",
        1067080124 to "별내",
        1067080125 to "퇴계원",
        1067080126 to "사릉",
        1067080127 to "금곡",
        1067080128 to "평내호평",
        1067080129 to "천마산",
        1067080130 to "마석",
        1067080131 to "대성리",
        1067080132 to "청평",
        1067080133 to "상천",
        1067080134 to "가평",
        1067080135 to "굴봉산",
        1067080136 to "백양리",
        1067080137 to "강촌",
        1067080138 to "김유정",
        1067080139 to "남춘천",
        1067080140 to "춘천",
        1075075209 to "청량리",
        1075075210 to "왕십리",
        1075075211 to "서울숲",
        1075075212 to "압구정로데오",
        1075075213 to "강남구청",
        1075075214 to "선정릉",
        1075075215 to "선릉",
        1075075216 to "한티",
        1075075217 to "도곡",
        1075075218 to "구룡",
        1075075219 to "개포동",
        1075075220 to "대모산입구",
        1075075221 to "수서",
        1075075222 to "복정",
        1075075223 to "가천대",
        1075075224 to "태평",
        1075075225 to "모란",
        1075075226 to "야탑",
        1075075227 to "이매",
        1075075228 to "서현",
        1075075229 to "수내",
        1075075230 to "정자",
        1075075231 to "미금",
        1075075232 to "오리",
        1075075233 to "죽전",
        1075075234 to "보정",
        1075075235 to "구성",
        1075075236 to "신갈",
        1075075237 to "기흥",
        1075075238 to "상갈",
        1075075239 to "청명",
        1075075240 to "영통",
        1075075241 to "망포",
        1075075242 to "매탄권선",
        1075075243 to "수원시청",
        1075075244 to "매교",
        1075075245 to "수원",
        1075075246 to "고색",
        1075075247 to "오목천",
        1075075248 to "어천",
        1075075249 to "야목",
        1075075250 to "사리",
        1075075251 to "한대앞",
        1075075252 to "중앙",
        1075075253 to "고잔",
        1075075254 to "초지",
        1075075255 to "안산",
        1075075256 to "신길온천",
        1075075257 to "정왕",
        1075075258 to "오이도",
        1075075259 to "달월",
        1075075260 to "월곶",
        1075075261 to "소래포구",
        1075075262 to "인천논현",
        1075075263 to "호구포",
        1075075264 to "남동인더스파크",
        1075075265 to "원인재",
        1075075266 to "연수",
        1075075267 to "송도",
        1075075268 to "인하대",
        1075075269 to "숭의",
        1075075270 to "신포",
        1075075271 to "인천",
        1077000684 to "신사",
        1077000685 to "논현",
        1077000686 to "신논현",
        1077000687 to "강남",
        1077000688 to "양재",
        1077000689 to "양재시민의숲",
        1077006810 to "청계산입구",
        1077006811 to "판교",
        1077006812 to "정자",
        1077006813 to "미금",
        1077006814 to "동천",
        1077006815 to "수지구청",
        1077006816 to "성복",
        1077006817 to "상현",
        1077006818 to "광교중앙",
        1077006819 to "광교",
        1081037410 to "판교",
        1081037411 to "이매",
        1081037412 to "삼동",
        1081037413 to "경기광주",
        1081037414 to "초월",
        1081037415 to "곤지암",
        1081037416 to "신둔도예촌",
        1081037417 to "이천",
        1081037418 to "부발",
        1081037419 to "세종왕릉",
        1081037420 to "여주",
        1092004701 to "북한산우이",
        1092004702 to "솔밭공원",
        1092004703 to "4.19 민주묘지",
        1092004704 to "가오리",
        1092004705 to "화계",
        1092004706 to "삼양",
        1092004707 to "삼양사거리",
        1092004708 to "솔샘",
        1092004709 to "북한산보국문",
        1092004710 to "정릉",
        1092004711 to "성신여대입구",
        1092004712 to "보문",
        1092004713 to "신설동",
        1093004001 to "일산",
        1093004002 to "풍산",
        1093004003 to "백마",
        1093004004 to "곡산",
        1093004005 to "대곡",
        1093004006 to "능곡",
        1093004007 to "김포공항",
        1093004008 to "원종",
        1093004009 to "부천종합운동장",
        1093004010 to "소사",
        1093004011 to "소새울",
        1093004012 to "시흥대야",
        1093004013 to "신천",
        1093004014 to "신현",
        1093004016 to "시흥시청",
        1093004017 to "시흥능곡",
        1093004018 to "달미",
        1093004019 to "선부",
        1093004020 to "초지",
        1093004021 to "시우",
        1093004022 to "원시"
    )

    fun SearchTrain(trainId: Int): String? {

        return TrainList[trainId]
    }
}