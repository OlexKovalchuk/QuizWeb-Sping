    var question_delete = /*[[#{msg.question-delete}]]*/
    var question_validate = /*[[#{msg.question-validate}]]*/
    var question_enter = /*[[#{msg.question-enter}]]*/
    var answer_add = /*[[#{msg.answer-add}]]*/
    var answer_delete = /*[[#{msg.answer-delete}]]*/
    var answer_enter =/*[[#{msg.answer-enter}]]*/
    var anwer_validate = /*[[#{msg.answer-validate}]]*/
    var description = /*[[#{msg.description}]]*/
    var check_box_validate = /*[[#{msg.checkbox-validate}]]*/

    var array = new Array();

    var qId = 0;
    var aId = 0;

    function loadQuestionCount(cnt) {
    qId = cnt;
    for (i = 0; i < qId; i++) {
    array.push(i);
}
}

    function loadAnswerCount(cnt) {
    aId = cnt;
}

    function createQuestion() {
    qId++;
    let questions = document.getElementById('questions');
    const question = document.createElement('li');
    question.id = 'question' + qId;
    question.innerHTML = '<input type="button" onclick="deleteQuestion(' + qId + ')" value="' + question_delete + '"/>' +
    '<h3>' + description + '</h3><input type="text" title="' + question_validate + '" maxlength="400" name="description' + qId +
    '" placeholder="' + question_enter + '" required/> <input type="button" onclick="createAnswer(' + qId + ')" value="' + answer_add + '"/>    <div style="visibility:hidden; color:red; " id="chk_option_error' + qId + '">' +
    +check_box_validate + '</div>';
    questions.appendChild(question);
    array.push(qId);
    createAnswer(qId);

}

    function createAnswer(id) {
    aId++;
    const question = document.getElementById('question' + id);
    const answer = document.createElement('div');
    answer.id = question.id + '-answer-' + aId;
    answer.innerHTML = '<input type="button" onclick="deleteAnswer(' + id + ',' + aId + ')" value="' + answer_delete + '"/>   <hr style=" clear: both;' +
    ' border: 1px solid transparent;  height: 0px;"> <input type="checkbox" name="question-' + question.id.substr(8) + '-answers" id="question-' + question.id.substr(8) + '-answers-' + aId + '" value="' + aId + '"/>' +
    ' <input type = "text" title="' + anwer_validate + '" maxlength="100" name="answer-' + aId + '" placeholder="' + answer_enter + '" required />';
    question.appendChild(answer);
    const answerArray = questionMap.get(parseInt(question.id.substr(8)));
    answerArray.push(aId);
}

    function deleteQuestion(id) {
    document.getElementById('question' + id).remove();
    removeA(array, id);
}

    function removeA(arr) {
    var what, a = arguments, L = a.length, ax;
    while (L > 1 && arr.length) {
    what = a[--L];
    while ((ax = arr.indexOf(what)) !== -1) {
    arr.splice(ax, 1);
}
}
    return arr;
}

    function deleteAnswer(id, id2) {
    document.getElementById('question' + id + "-answer-" + id2).remove();

}

    function getAnswerCount() {
    return aId;
}

    function handleData() {
    var form_data = new FormData(document.querySelector('form'));
    for (const value of array) {
    if (!form_data.has('question-' + value + '-answers')) {
    document.getElementById("chk_option_error" + value).style.visibility = "visible";
    return false
}
    document.getElementById("chk_option_error" + value).style.visibility = "hidden";
}
    return true;
}
