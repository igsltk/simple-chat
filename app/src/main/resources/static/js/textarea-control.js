document.addEventListener('DOMContentLoaded', (event) => {

	const textarea = document.getElementById('message');
	if (textarea) {
		const charCount = document.getElementById('char-count');
		const minHeight = 100;
		const maxHeight = 120;
		
		textarea.addEventListener('input', () => {
			if (textarea.value.length > 100)
				textarea.value = textarea.value.slice(0, 100);
			charCount.textContent = `${textarea.value.length}/100`;
			textarea.style.setProperty("height", "0");
			textarea.style.setProperty(
				"height",
				Math.max(Math.min(textarea.scrollHeight, maxHeight), minHeight) + "px",
			);
		});
	}

});