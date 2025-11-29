

'use strict'

const getStoredTheme = () => localStorage.getItem('theme')
const setStoredTheme = theme => localStorage.setItem('theme', theme)

const getPreferredTheme = () => {
	const storedTheme = getStoredTheme()
	if (storedTheme) {
		return storedTheme
	}

	return window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'light'
}

const changeSVG = (theme) => {
	let svg = document.querySelectorAll('svg');
	if(theme === 'dark'){
		for(const s of svg){
			if (!s.classList.contains('text-success') && !s.classList.contains('text-danger') && !s.classList.contains('text-warning')) {
				s.style.fill = 'rgb(255,255,255)';
			}
		}
	}else{
		for(const s of svg){
			if (!s.classList.contains('text-success') && !s.classList.contains('text-danger') && !s.classList.contains('text-warning')) {
				s.style.fill = 'rgb(0,0,0)';
			}
		}
	}
}

const setTheme = (theme) => {
	if (theme === 'auto') {
		document.documentElement.setAttribute('data-bs-theme', (window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'light'))
	} else {
		document.documentElement.setAttribute('data-bs-theme', theme)
	}
	changeSVG(theme === 'auto' ? getPreferredTheme() : theme)
}

setTheme(getPreferredTheme())

window.matchMedia('(prefers-color-scheme: dark)').addEventListener('change', () => {
	const storedTheme = getStoredTheme()
	if (storedTheme !== 'light' && storedTheme !== 'dark') {
		setTheme(getPreferredTheme())
	}
})

window.addEventListener('DOMContentLoaded', () => {
	setTheme(getPreferredTheme())

	let button = document.querySelector('#switchTheme');
	if(button){
		button.addEventListener('click', () => {
			switchTheme();
		})
	}
})

const switchTheme = () => {
	let theme = getStoredTheme();
	if (!theme) {
		theme = getPreferredTheme();
	}
	
	let themeValue = (theme === 'light') ? 'dark' : 'light';
	setStoredTheme(themeValue);
	setTheme(themeValue);
}