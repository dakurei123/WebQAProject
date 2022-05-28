var userId
var userRole
$.get("http://localhost:8081/user", function(result) {
    userId = result.id
    userRole = result.role
});

function getQuestions(url, page, key) {
    $.ajax({
        url: url,
        type: 'get',
        data: {
            page: page,
            key: key
        },
        success: function(result) {
            var news = $('.news-container')
            news.empty()
            for (var i of result) {
                var date = new Date(i.createdDate);
                date = convertMonth(date.getMonth()) + " " + date.getDate() + " " + date.getHours() + ":" + date.getMinutes()
                var res = `<div class="item" style="position: relative">`
                if (userRole == 'ROLE_ADMIN') {
                    res += `<button class="red deleteQuestionBtn" idq="${i.id}">X</button>`
                }
                res += `
                        <div class="user"> 
                            <a href="http://localhost:8081/profile/${i.user_id}"> 
                                <img src="http://localhost:8081/${i.avatarLink}" alt="" class="avatar" width="40px" height="40px" /> 
                            </a> 
                            <div class="modify"> 
                                <strong><a class="modifiedby" href="http://localhost:8081/profile/${i.user_id}">${i.fullName}</a></strong>
                                <p class="modifieddate">${date}</p>
                            </div> 
                        </div> 
                        <div class="new"> 
                            <div class="category-new">${i.categoryName}</div> 
                            <h1 class="title"> 
                                <a href="/question/${i.id}">${i.title}</a> 
                            </h1> 
                        </div> 
                    </div>
                `
                news.append(res)
            }
            if (userRole == 'ROLE_ADMIN') {
                var deleteQuestionBtns = document.querySelectorAll('.deleteQuestionBtn')
                for (const deleteQuestionBtn of deleteQuestionBtns) {
                    deleteQuestionBtn.addEventListener('click', function(event) {
                        event.stopImmediatePropagation()
                        event.stopPropagation()
                        var idQuestion = deleteQuestionBtn.getAttribute('idq')
                        if (confirm('Are you sure you want delete this question?')) {
                            $.ajax({
                                url: 'http://localhost:8081/api/question/' + idQuestion,
                                type: 'delete',
                                success: function(result) {
                                    if (result == true) {
                                        var elementDelete = deleteQuestionBtn.closest('.item')
                                        elementDelete.remove()
                                        alert('Deleted')
                                    } else {
                                        alert('Delete error')
                                        location.reload()
                                    }
                                },
                                error: function(error) {
                                    alert('Delete error')
                                    location.reload()
                                }
                            })
                        }
                    })
                }
            }
        },
        error: function(error) {
            alert(error.responseText)
            window.location = "/login";
        }
    })
}