import textui
import os
import subprocess

# 1. Get the files to compile

files = []
def checkfolder(folder):
	for file in os.listdir(folder):
		newname = os.path.join(folder, file)
		if os.path.isdir(newname):
			checkfolder(newname)
		else:
			files.append(newname)
checkfolder("src")

# 2. Compile the files

if textui.getbool(f"Do you want to compile all {len(files)} files?"):
	print("Compiling all files...")
	os.makedirs("compiled_output", exist_ok=True)
	cmd = ["javac", "-g", "-d", "compiled_output", "-cp", "src"]
	cmd.extend(files)
	subprocess.run(cmd)
else:
	toc = textui.getboollist("Which files do you want to compile?", files)
	print("Compiling selected files...")
	cmd = ["javac", "-g", "-d", "compiled_output", "-cp", "src"]
	cmd.extend([files[i] for i in range(len(files)) if toc[i]])
	subprocess.run(cmd)

# 3. Zip the files

print("Saving...")
f = open("compiled_output/manifest", "w")
f.write(f"""Manifest-Version: 1.0
Created-By: 17.0.3 (GraalVM Community)
Main-Class: com.sillypantscoder.processingclone.Main

""")
f.close()
subprocess.run(["jar", "-c", "-v", "-f", "compiled.jar", "-m", "compiled_output/manifest", "-C", "compiled_output/", "."])
subprocess.run(["rm", "-r", "compiled_output"])
print("Running...")
subprocess.run(["java", "-jar", "compiled.jar"])