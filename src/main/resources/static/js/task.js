console.log('work');
document.querySelector('button').addEventListener('click', getText);


function getText(){
  var m = querySelector('.answer').value;
  console.log(m);
  let a = document.querySelector('.Answer').value;
  console.log(a);
  document.querySelector('.out').innerHTML = a;
}
