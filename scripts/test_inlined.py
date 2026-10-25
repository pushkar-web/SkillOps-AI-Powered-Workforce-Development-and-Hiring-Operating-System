import os
import tempfile
import subprocess

with open('scripts/mermaid.min.js', 'r', encoding='utf-8') as f:
    mermaid_js = f.read()

mermaid_diagram = """erDiagram
    USERS ||--o{ SESSIONS : has
    USERS {
      bigint id PK
      varchar email
    }
    SESSIONS {
      bigint id PK
      bigint user_id FK
    }"""

test_html = """<!DOCTYPE html>
<html>
<head>
  <script>""" + mermaid_js + """</script>
</head>
<body style="background: white; padding: 20px;">
  <pre class="mermaid">""" + mermaid_diagram + """</pre>
  <script>
    mermaid.initialize({ startOnLoad: true });
  </script>
</body>
</html>"""

with open('docs/test_inlined.html', 'w', encoding='utf-8') as f:
    f.write(test_html)

tmp_dir = tempfile.mkdtemp()
out_png = os.path.abspath('docs/test_inlined.png')
html_file = os.path.abspath('docs/test_inlined.html')
file_url = 'file:///' + html_file.replace('\\', '/')

chrome = r'C:\Program Files\Google\Chrome\Application\chrome.exe'
cmd = [
    chrome,
    '--headless=new',
    f'--user-data-dir={tmp_dir}',
    '--disable-gpu',
    '--no-first-run',
    '--no-default-browser-check',
    f'--screenshot={out_png}',
    '--window-size=1200,800',
    '--virtual-time-budget=4000',
    file_url
]
print("Running chrome screenshot on inlined html...")
res = subprocess.run(cmd, capture_output=True, text=True)
print("Returncode:", res.returncode)
if os.path.exists(out_png):
    print("PNG size:", os.path.getsize(out_png))
