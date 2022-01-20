<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <div>
                <a class="navbar-brand" href="${pageContext.request.contextPath}/">
                    <img alt="Brand" src="${pageContext.request.contextPath}/static/favicon.png"  alt="" width="50" height="50">
                </a>
            </div>
            <div class="collapse navbar-collapse navbar-light" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbar-dropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Theme
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbar-dropdown">
                            <li><a class="dropdown-item" href="<c:url value="/form/themes"/>">Create New</a></li>
                            <li><a class="dropdown-item" href="<c:url value="/view/themes"/>">Show All</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>