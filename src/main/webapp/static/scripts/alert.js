var Alert = (function(){
  function Alert(message,element){
    $(element).html(message).removeClass('hide');
  }
  return Alert;
})();
