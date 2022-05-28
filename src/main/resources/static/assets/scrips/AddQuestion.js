var x;
var quill = new Quill('#ql-content', {
    theme: 'snow'
});
$('.add-ques').click(function() {
    $('.form-question').css('display', 'flex')
})
$('.form-question').click(function() {
    $('.form-question').css('display', 'none')
})

const mainForm = document.querySelector('.main-form')
mainForm.addEventListener('click', function(event) {
    event.stopPropagation()
})

var addQuestion = function() {
    var data = {};
    data["content"] = quill.root.innerHTML;
    data["title"] = document.querySelector("#title").value;
    data["category_id"] = document.querySelector("#category").value;
    $.ajax({
        url: '/api/question',
        type: 'post',
        contentType: 'application/json',
        data: JSON.stringify(data),
        dataType: 'json',
        success: function(result) {
            alert("Success")
            window.location = "/question/" + result;
        },
        error: function(error) {
            alert(error.responseText)
            if (error.status == "403")
                window.location = "/login"
        }
    })
}
$('#add-question-js').click(addQuestion);