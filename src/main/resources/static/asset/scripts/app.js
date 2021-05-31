const navLinks = document.querySelector(".links");
const toggle = document.querySelector(".toggle button")

console.log(toggle);

toggle.addEventListener('click', () =>{
    navLinks.classList.toggle("show-nav");
})