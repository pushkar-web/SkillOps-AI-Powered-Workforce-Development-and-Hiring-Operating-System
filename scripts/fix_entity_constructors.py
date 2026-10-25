import os
import re

BASE_DIR = os.path.join("src", "main", "java", "com", "skillops")

fixed_count = 0

for root, dirs, files in os.walk(BASE_DIR):
    if "entity" in root:
        for f in files:
            if f.endswith(".java"):
                file_path = os.path.join(root, f)
                with open(file_path, "r", encoding="utf-8") as fp:
                    content = fp.read()

                class_name = f.replace(".java", "")

                # Check if @NoArgsConstructor is present and public ClassName() is not present
                has_no_args = "@NoArgsConstructor" in content
                has_explicit_ctor = f"public {class_name}()" in content

                if has_no_args or not has_explicit_ctor:
                    # Remove @NoArgsConstructor annotation and import if possible
                    new_content = re.sub(r"@NoArgsConstructor\s*\n", "", content)
                    new_content = re.sub(r"import lombok\.NoArgsConstructor;\s*\n", "", new_content)

                    # Add explicit constructor after class declaration if not already there
                    if f"public {class_name}()" not in new_content:
                        # Find the class declaration line: public class ClassName ... {
                        pattern = rf"(public\s+class\s+{class_name}[^{{]*\{{)"
                        ctor_code = f"\n\n    public {class_name}() {{\n    }}\n"
                        new_content = re.sub(pattern, rf"\1{ctor_code}", new_content, count=1)

                    if new_content != content:
                        with open(file_path, "w", encoding="utf-8") as fp:
                            fp.write(new_content)
                        fixed_count += 1
                        print(f"Fixed constructor for entity: {class_name}")

print(f"\nSuccessfully added explicit constructors to {fixed_count} entity classes!")
