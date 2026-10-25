import os
import tempfile
import subprocess

tmp_dir = tempfile.mkdtemp()
out_png = os.path.abspath('docs/quick_test.png')
html_file = os.path.abspath('docs/quick_test.html')
file_url = 'file:///' + html_file.replace('\\', '/')

chrome = r'C:\Program Files\Google\Chrome\Application\chrome.exe'
cmd = [
    chrome,
    '--headless=new',
    f'--user-data-dir={tmp_dir}',
    '--disable-gpu',
    '--no-first-run',
    '--no-default-browser-check',
    '--allow-file-access-from-files',
    f'--screenshot={out_png}',
    '--window-size=1600,1200',
    '--virtual-time-budget=5000',
    file_url
]
print("Running chrome screenshot with isolated profile...")
res = subprocess.run(cmd, capture_output=True, text=True)
print("Returncode:", res.returncode)
print("PNG exists:", os.path.exists(out_png))
if os.path.exists(out_png):
    print("PNG size:", os.path.getsize(out_png))
