const header = document.querySelector('.header')

window.addEventListener('scroll', () => {
    const scrollPosition = window.scrollY;
    const maxShift = window.innerWidth - header.offsetWidth;
    const newLeft = Math.min(scrollPosition / 2, maxShift);

    header.style.transform = 'translateX(${newLeft}px)';
});