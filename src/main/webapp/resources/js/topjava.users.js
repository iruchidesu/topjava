const userAjaxUrl = "admin/users/";

// https://stackoverflow.com/a/5064235/548473
let ctx = {
    ajaxUrl: userAjaxUrl,
    updateTable: updateTable
};

function updateTable() {
    $.get(ctx.ajaxUrl, updateTableReDraw)
}

function enable(chkbox, id) {
    let enabled = chkbox.is(":checked");
//  https://stackoverflow.com/a/22213543/548473
    if (confirm("Are you sure?")) {
        $.ajax({
            url: userAjaxUrl + id,
            type: "POST",
            data: "enabled=" + enabled
        }).done(function () {
            chkbox.closest("tr").attr("data-userEnabled", enabled);
            successNoty(enabled ? "Enabled" : "Disabled");
        }).fail(function () {
            $(chkbox).prop("checked", !enabled);
        });
    } else {
        $(chkbox).prop("checked", !enabled);
    }
}

// $(document).ready(function () {
$(function () {
    makeEditable(
        $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "name"
                },
                {
                    "data": "email"
                },
                {
                    "data": "roles"
                },
                {
                    "data": "enabled"
                },
                {
                    "data": "registered"
                },
                {
                    "defaultContent": "Edit",
                    "orderable": false
                },
                {
                    "defaultContent": "Delete",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0,
                    "asc"
                ]
            ]
        })
    );
});