document.addEventListener("DOMContentLoaded", function() {
    fetch('menu.html')
        .then(response => response.text())
        .then(data => {
            document.getElementById('menu-container').innerHTML = data;
            initializeSearchHandlers();
        });
});

function initializeSearchHandlers() {
    const searchForm = document.getElementById('searchForm');
    if (searchForm) {
        searchForm.addEventListener('submit', function(e) {
            e.preventDefault();
            const searchTerm = document.getElementById('searchInput').value.trim();
            if (searchTerm) {
                window.location.href = `Busqueda.html?q=${encodeURIComponent(searchTerm)}`;
            }
        });
    }

    const searchSuggestions = document.querySelectorAll('.search-suggestion');
    searchSuggestions.forEach(suggestion => {
        suggestion.addEventListener('click', function() {
            const suggestionText = this.textContent.trim();
            document.getElementById('searchInput').value = suggestionText;
            window.location.href = `Busqueda.html?q=${encodeURIComponent(suggestionText)}`;
        });
    });

    const searchInput = document.getElementById('searchInput');
    if (searchInput) {
        searchInput.addEventListener('keypress', function(e) {
            if (e.key === 'Enter') {
                e.preventDefault();
                const searchTerm = this.value.trim();
                if (searchTerm) {
                    window.location.href = `Busqueda.html?q=${encodeURIComponent(searchTerm)}`;
                }
            }
        });
    }
}
