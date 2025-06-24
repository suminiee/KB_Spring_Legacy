<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
<%--    <h3>V1</h3>--%>
<%--    <a href="/">Home</a>--%>
<%--    <a href="/member/list">회원 목록</a>--%>
<%--    <a href="/member/add">회원 추가</a>--%>

    <h3>V2</h3>
    <a href="/">Home</a>
    <a href="/member/v2/list">회원 목록</a>
    <a href="/member/v2/add">회원 추가</a>

    <h3>POST</h3>
    <a href="/post/list">게시판 글 목록</a>
    <a href="/post/compare">DB 속도 비교</a>
    <a href="/post/404">404 에러</a>
<%--    <a href="/post/error">500 에러</a>--%>

    <h3>게시판 v2</h3>
    <a href="/post/v2/list">REST API 게시글</a>

    <h3>security</h3>
    <a href="/">home</a>
    <a href="/user/register">회원가입</a>
    <a href="/user/login">로그인</a>
    <a href="/admin">관리자</a>
    <a href="/member">멤버</a>
    <a href="#" onclick="document.getElementById('logout-form').submit();">로그아웃</a>
    <form id="logout-form" action="/user/logout" method="post" style="display: none;">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</div>