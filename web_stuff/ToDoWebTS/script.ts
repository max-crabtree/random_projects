interface Note {
    title: string,
    text: string
}

const noteList = document.querySelector<HTMLUListElement>('#note-list')

function showTitlePopup(): void {
    titlePopup?.setAttribute('display', 'block')
}

addTask.addEventListener('click', () => showTitlePopup);