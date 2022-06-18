<!doctype html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Multi</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
</head>
<body>

<div style="padding: 20px">

    <a href="/test/tf">True/False</a>
    <a href="/test/single">Single</a>
    <a href="/test/multi">Multi</a>
    <br>

    <form action="/test/multi" method="post">

    <#list questions as question>
        <div class="card">
            <div class="card-body">
                <p class="card-text">${question.text}</p>
                <#list question.answers as answer>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" name="answer_${question.id}" id="answer_${question.id}" value="${answer.id}">
                        <label class="form-check-label" for="answer">
                            ${answer.text}
                        </label>
                    </div>
                </#list>
                <div id="result_${question.id}"></div>
            </div>
        </div>
        <br>
    </#list>

        <button id="submit" type="button" class="btn">Submit</button>
    </form>
    <br>
    <div class="badge bg-primary text-wrap" hidden style="width: 6rem;" id="score">
    </div>

</div>


<script src="https://code.jquery.com/jquery-3.6.0.js"
        integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#submit").click(function () {

            let answers = [];

            for (let i = 0; i < 22; i++) {
                let answer = {
                    id: i,
                    answers: getSelectedCheckBoxValues('answer_' + i),
                };
                answers.push(answer)
            }

            $.ajax({
                url: '/test/multi',
                method: 'post',
                data: {
                    'answers': JSON.stringify({
                        answers: answers
                    })
                },
                success: function (results) {
                    let results1 = results.results
                    let score = 0
                    for (let i = 0; i < results1.length; i++) {
                        let res = results1[i]
                        var success = true
                        for (let j = 0; j < res.rightAnswerIds.length; j++) {
                            var d = false
                            for (var resKey in res.answerIds) {
                                if (resKey === res.rightAnswerIds[j]) {
                                    d = true
                                }
                            }
                            if (d === false) {
                                success = false
                            }
                        }
                        if (success) {
                            document.getElementById("result_"+i).innerHTML =
                                '<div class="badge bg-success text-wrap">Correct</div>'
                            score = score + 25
                        } else {
                            document.getElementById("result_"+i).insertAdjacentHTML('beforeend', '<div class="badge bg-danger text-wrap">Incorrect</div>')

                            document.getElementById("result_"+i).insertAdjacentHTML('beforeend', '<p>Right answers:</p>')

                            for (var resKey in res.rightAnswerTexts) {
                                document.getElementById("result_"+i).insertAdjacentHTML('beforeend', '<p style="font-weight: bold">'+res.rightAnswerTexts[resKey] +'</p>')
                            }
                        }
                    }
                    document.getElementById("score").hidden = false
                    document.getElementById("score").innerText = 'Score: '+ score + '/550'
                    document.getElementById("submit").hidden = true
                },
                error: function (response) {
                    alert('Error' + response)
                }
            })
        });
    });

    function getSelectedCheckBoxValues(name) {
        var checkboxes = document.getElementsByName(name);
        var values = []
        for (var i = 0, length = checkboxes.length; i < length; i++) {
            if (checkboxes[i].checked) {
                values.push(checkboxes[i].value)
            }
        }
        return values
    }

</script>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
</body>
</html>