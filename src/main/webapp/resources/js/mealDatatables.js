var ajaxUrl = "ajax/profile/meals/";
var datatableApi;

function updateTable() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "filter",
        data: $("#filter").serialize()
    }).done(updateTableByData);
}

function clearFilter() {
    $("#filter")[0].reset();
    $.get(ajaxUrl, updateTableByData);
}

$(function () {
    datatableApi = $("#datatable").DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "dateTime",
                "render" : function (data, type, row) {
                    var array = data.toString().split("T");
                    var date = array[0];
                    var time = array[1];
                    return date + " " + time;
                }
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
            },
            {
                "defaultContent": "Edit",
                "orderable": false,
                "render" : renderEditBtn
            },
            {
                "defaultContent": "Delete",
                "orderable": false,
                "render" : renderDeleteBtn
            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ],
        "createdRow" : function (row, data, dataIndex) {
            if (data.exceed){
                $(row).attr("data-mealExceed", true);
            } else {
                $(row).attr("data-mealExceed", false);
            }
        },
        "initComplete" : makeEditable()
    });
});