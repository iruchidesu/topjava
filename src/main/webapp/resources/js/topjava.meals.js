const mealAjaxUrl = "profile/meals/";

// https://stackoverflow.com/a/5064235/548473
const ctx = {
    ajaxUrl: mealAjaxUrl,
    updateTable: function () {
        $.ajax({
            type: "GET",
            url: mealAjaxUrl + "filter",
            data: $("#filter").serialize()
        }).done(updateTableByData);
    }
}

function clearFilter() {
    $("#filter")[0].reset();
    $.get(mealAjaxUrl, updateTableByData);
}

// http://api.jquery.com/jQuery.ajax/#using-converters
$.ajaxSetup({
    converters: {
        "text json": function (text) {
            let json = JSON.parse(text);
            $(json).each(function () {
                if (this.hasOwnProperty("dateTime")) {
                    this.dateTime = this.dateTime.substr(0, 16).replace('T', ' ');
                }
            });
            return json;
        }
    }
});

$(function () {
    makeEditable(
        $("#datatable").DataTable({
            "ajax": {
                "url": mealAjaxUrl,
                "dataSrc": ""
            },
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime"
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
                },
                {
                    "defaultContent": "",
                    "orderable": false,
                    "render": renderEditBtn
                },
                {
                    "defaultContent": "",
                    "orderable": false,
                    "render": renderDeleteBtn
                }
            ],
            "createdRow": function (row, data) {
                $(row).attr("data-mealExcess", data.excess);
            },
            "order": [
                [
                    0,
                    "desc"
                ]
            ]
        })
    );

    $.datetimepicker.setLocale(i18n["localCode"]);

//  http://xdsoft.net/jqplugins/datetimepicker/
    $('#startDate').datetimepicker({
        timepicker: false,
        format: 'Y-m-d',
        formatDate: 'Y-m-d',
    });
    $('#endDate').datetimepicker({
        timepicker: false,
        format: 'Y-m-d',
        formatDate: 'Y-m-d',
    });

    $('#startTime').datetimepicker({
        datepicker: false,
        format: 'H:i',
    });
    $('#endTime').datetimepicker({
        datepicker: false,
        format: 'H:i',
    });

    $('#dateTime').datetimepicker({
        format: 'Y-m-d H:i'
    });
});