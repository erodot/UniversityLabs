var faculties;

function goBack() {
    window.history.back();
}

function updateDepartments(isDefault) {
    var fac = $("#facultyID").children("option").filter(":selected").val() - 1;;
    var depList = $('#departmentID');

    $('#departmentID option').remove();

    if(isDefault)
    $('#departmentID').add('<option value=' + faculties[0].DepartmentsList[0].DepartmentID + '>' + faculties[0].DepartmentsList[0].DepartmentName + '</option>').appendTo(depList);

    if(fac > 0)
    for (var i = 0; i < faculties[fac].DepartmentsList.length ; i++)
    {
        var option_str = '<option value=' + faculties[fac].DepartmentsList[i].DepartmentID + '>' + faculties[fac].DepartmentsList[i].DepartmentName + '</option>';
        $('#departmentID').add(option_str).appendTo(depList);
    }
}