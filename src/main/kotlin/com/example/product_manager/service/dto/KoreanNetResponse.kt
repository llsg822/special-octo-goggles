package com.example.product_manager.service.dto

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement(localName = "mgs1OutXml")
data class KoreanNetResponse(
    val gtin: String?, // 바코드 번호
    val npname: String?, // 이번 개편으로 제외된 항목, 빈값처리, 상품명(옵션항목 제외)
    val conamek: String?, // 회사명(한글)
    val conamee: String?, // 회사명(영문)
    val imgpath1: String?, // 정면 이미지 경로
    val imgpath2: String?, // 측면 이미지 경로
    val imgpath3: String?, // 바코드 이미지 경로
    val dscrgtink: String?, // 상품명 (옵션포함)
    val dscrbrandk: String?, // 브랜드명
    val countrydescr: String?, // 제조국가(원산지)
    val dstartavailable: String?, // 최초출시 일자
    val dsysupdated: String?, // MGS1 테이블 등록일자
    val text: String?, // 상품설명
    val pgurl: String?, // 상품홍보페이지URL로 변경됨(이전 번전 API에서의 의미, 상품 페이지 URL)
    val kanclasscode: String?, // KAN분류코드
    val unitnetcont: Int?, // 순내용, 이번 개편으로 제외된 항목, 빈값처리
    val unitnetcontuomdescr: String?, // 순내용 단위명세, 이번 개편으로 제외된 항목, 빈값처리
    val unitnetcontuom: String?, // 순내용 단위, 이번 개편으로 제외된 항목, 빈값처리
    val unitsinpack: Int?, // 박스당 입수
    val height: Int?, // 높이
    val heightuomdescr: String?, // 높이 단위 명세
    val heightuom: String?, // 높이 단위
    val width: String?, // 너비
    val widthuomdescr: String?, // 너비단위 명세
    val widthuom: String?, // 너비단위
    val depth: Int?, // 깊이
    val depthuomdescr: String?, // 깊이단위 명세
    val depthuom: String?, // 깊이단위
    val netweight: Int?, // 순중량
    val netweightuomdescr: String?, //순중량 단위명세
    val netweightuom: String?, //순중량 단위명세
    val grossweight: Int?, // 총중량
    val grossweightuomdescr: String?, // 총중량 단위명세
    val grossweightuom: String?, // 총중량 단위
    val imgpath4: String?, // 바코드 이미지 경로
    val kbn: String?, // 사업자등록번호
    val status: String, // 유통표준코드 회원여부
    val gln: String?, // GLN
    val packgtin: String?, // 패키지상품에 포함된 상품바코드들(구분자, |)
    val prgubun: String?, // 상품구분
    val manufact: String?, // 제조사
    val volume: String?, // 내용량
    val volumeunit: String?, // 내용량단위
    val taxtype: String?, // 과세형태
    val rrpyn: String?, // RRP여부
    val rrphgt: String?, // RRP높이
    val rrpwdt: String?, // RRP너비
    val rrpdth: String?, // RRP길이
    val rrpunit: String?, // RRP단위
    val seasoncode: String?, // 시즌코드
    val seasonname: String?, // 시즌명
    val sizecode: String?, // 사이즈코드
    val sizename: String?, // 사이즈명
    val colorcode: String?, // 색상코드
    val colorname: String?, // 색상명
    val dataType: String?, //바코드검증여부(B1:상품등록, B2:검증완료)
)
