function getFollow() {
    $.get("/api/user/follow", function(result) {
        reset = document.querySelector('.follows')
        reset.innerHTML = '<ul><h1>Followed</h1></ul>'
        if (result == '')
            return
        const follows = $('.follows ul')
        for (const follow of result) {
            res = `
                <li><a href="https://hungdeptraino1.herokuapp.com//profile/${follow.followedId}" style="display: flex;align-items: center;">
                    <img src="https://hungdeptraino1.herokuapp.com//${follow.followAvatar}" alt="" class="avatar" width="36px" height="36px" />
                    <strong style="font-size: 20px;padding-left: 8px;">${follow.followName}</strong>
                </a></li>
            `
            follows.append(res)
        }
    })
}