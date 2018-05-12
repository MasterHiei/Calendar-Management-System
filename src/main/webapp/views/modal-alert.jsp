<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/modal-alert.css">
<div class="modal fade" id="dynamicModal" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <p class="modal-title h3"></p>
            </div>
            <div class="modal-body"></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" id="modalCancelBtn">取消</button>
                <button type="button" class="btn btn-primary" id="modalConfirmBtn">确认</button>
            </div>
        </div>
    </div>
</div>